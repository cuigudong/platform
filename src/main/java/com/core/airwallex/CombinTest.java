package com.core.airwallex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class CombinTest {

	private CombinTest() {

	}

	public static CombinTest test = null;

	public static CombinTest getInstance() {
		if (test == null) {
			test = new CombinTest();
		}
		return test;
	}

	public static void main(String args[]) {
		List<CbRmbTranAssistDtl> orders = getInstance().createOrders();
	
		getInstance().payForCcbToAriwallex(orders);
	}

	private List<CbRmbTranAssistDtl> createOrders() {
		List<CbRmbTranAssistDtl> orders = Lists.newArrayList();
		CbRmbTranAssistDtl o1=new CbRmbTranAssistDtl();
		o1.setGopayOrderId("1231");
		o1.setTradeCode("2000");
		o1.setAmt(new BigDecimal(1900));
		orders.add(o1);
		CbRmbTranAssistDtl o2=new CbRmbTranAssistDtl();
		o2.setGopayOrderId("1232");
		o2.setTradeCode("2000");
		o2.setAmt(new BigDecimal(1900));
		orders.add(o2);
		CbRmbTranAssistDtl o3=new CbRmbTranAssistDtl();
		o3.setGopayOrderId("1233");
		o3.setTradeCode("2000");
		o3.setAmt(new BigDecimal(100));
		orders.add(o3);
		CbRmbTranAssistDtl o4=new CbRmbTranAssistDtl();
		o4.setGopayOrderId("1234");
		o4.setTradeCode("2000");
		o4.setAmt(new BigDecimal(101));
		orders.add(o4);
		return orders;
	}

	public void payForCcbToAriwallex(List<CbRmbTranAssistDtl> cbRmbTranAssistDtls) {
		Table<String, BigDecimal, String> tables = getCombineOrder(cbRmbTranAssistDtls);
		Set<Cell<String, BigDecimal, String>> cells = tables.cellSet();
		for (Cell<String, BigDecimal, String> temp : cells) {
			System.out.println(String.format("combin order batchId:%s,amt:%s,gopayorderid:%s,", temp.getRowKey(),
					temp.getColumnKey(), temp.getValue()));
		}
	}

	private Table<String, BigDecimal, String> getCombineOrder(List<CbRmbTranAssistDtl> cbRmbTranAssistDtls) {
		System.out.println("getCombineOrder combin order ");
		Table<String, BigDecimal, String> tables = HashBasedTable.create();
		Table<String, BigDecimal, String> tables100 = HashBasedTable.create();
		for (CbRmbTranAssistDtl order : cbRmbTranAssistDtls) {
			if ("2000".contains(order.getTradeCode())) {
				System.out.println("200ordre:" + order.getGopayOrderId());
				createCombineOrder("T", tables, order);
			} else if ("1000".contains(order.getTradeCode())) {
				System.out.println("100ordre:" + order.getGopayOrderId());
				createCombineOrder("O", tables100, order);
			} else {
				System.out.println("this ordre is error not 200W or 100W:" + order.getGopayOrderId());
			}
		}
		Set<Cell<String, BigDecimal, String>> cells = tables100.cellSet();
		for (Cell<String, BigDecimal, String> temp : cells) {
			System.out.println(String.format("100 combin order batchId:%s,amt:%s,gopayorderid:%s,", temp.getRowKey(),
					temp.getColumnKey(), temp.getValue()));
			tables.put(temp.getRowKey(), temp.getColumnKey(), temp.getValue());
		}

		Set<Cell<String, BigDecimal, String>> cell = tables.cellSet();
		for (Cell<String, BigDecimal, String> temp : cell) {
			System.out.println(String.format("合并的订单为:combin order batchId:%s,amt:%s,gopayorderid:%s,", temp.getRowKey(),
					temp.getColumnKey(), temp.getValue()));
		}
		return tables;
	}

	private void createCombineOrder(String prefix, Table<String, BigDecimal, String> tables,
			CbRmbTranAssistDtl order) {
		BigDecimal payMaxAmt = prefix.equals("T") ? new BigDecimal(2000) : new BigDecimal(1000);
		System.out.println("maxAMT:" + payMaxAmt.toString());
		Long start = System.currentTimeMillis();
		if (tables.size() > 0) {
			Set<Cell<String, BigDecimal, String>> cells = tables.cellSet();
			int cellsSize = 0;
			for (Cell<String, BigDecimal, String> temp : cells) {
				System.out.println(String.format("createCombineOrder batchId:%s,amt:%s,gopayorderid:%s,",
						temp.getRowKey(), temp.getColumnKey(), temp.getValue()));
				BigDecimal totalAmt = temp.getColumnKey().add(order.getAmt());
				if (totalAmt.compareTo(payMaxAmt) > 0) {
					cellsSize++;
				} else {
					tables.put(temp.getRowKey(), temp.getColumnKey().add(order.getAmt()),
							temp.getValue().concat(",").concat(order.getGopayOrderId()));
					tables.remove(temp.getRowKey(), temp.getColumnKey());
					break;
				}
				if (cellsSize == cells.size()) {
					tables.put(prefix + order.getGopayOrderId(), order.getAmt(), order.getGopayOrderId());
				}
			}
		}
		if (tables.size() == 0) {
			tables.put(prefix + order.getGopayOrderId(), order.getAmt(), order.getGopayOrderId());
		}
		Long end = System.currentTimeMillis();
		System.out.println("createCombineOrder st:" + (end - start));
	}

	public class CbRmbTranAssistDtl {
		private String tradeCode;
		private String gopayOrderId;
		private BigDecimal amt;

		public String getTradeCode() {
			return tradeCode;
		}

		public void setTradeCode(String tradeCode) {
			this.tradeCode = tradeCode;
		}

		public String getGopayOrderId() {
			return gopayOrderId;
		}

		public void setGopayOrderId(String gopayOrderId) {
			this.gopayOrderId = gopayOrderId;
		}

		public BigDecimal getAmt() {
			return amt;
		}

		public void setAmt(BigDecimal amt) {
			this.amt = amt;
		}

	}
}
