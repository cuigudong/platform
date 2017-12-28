package com.core.airwallex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

/**
 * this si
 */
public class CombinOrderTest {

	public static void main(String args[]) {
		
		BigDecimal ss=new BigDecimal(Integer.MAX_VALUE);
		System.out.println(ss.toString());
		
//		List<Order> orders = createOrder();
//		Table<String, BigDecimal, String> tables = HashBasedTable.create();
//		for (Order order : orders) {
//			if (tables.size() > 0) {
//				Set<Cell<String, BigDecimal, String>> cells = tables.cellSet();
//				int cellsSize = 0;
//				for (Cell<String, BigDecimal, String> temp : cells) {
//					BigDecimal totalAmt = temp.getColumnKey().add(order.getAmt());
//					if (totalAmt.compareTo(new BigDecimal(100)) > 0) {
//						cellsSize++;
//					} else {
//						tables.put(temp.getRowKey(), temp.getColumnKey().add(order.getAmt()),
//								temp.getValue().concat(",").concat(order.getId()));
//						System.out.println("加入:" + temp.getRowKey() + "," + temp.getColumnKey().add(order.getAmt()));
//						tables.remove(temp.getRowKey(), temp.getColumnKey());
//						System.out.println("移除:" + temp.getRowKey() + "," + temp.getColumnKey());
//					}
//					if (cellsSize == cells.size()) {
//						tables.put("T"+order.getId()+"随机数", order.getAmt(), order.getId());
//						System.out.println("直接加入:" + order.getAmt() + "," + order.getId());
//					}
//					System.out.println("&&&" + temp.getRowKey() + " " + temp.getColumnKey() + " " + temp.getValue());
//				}
//			}
//			if (tables.size() == 0) {
//				tables.put("T"+order.getId()+"随机数", order.getAmt(), order.getId());
//			}
//		}
//		Set<Cell<String, BigDecimal, String>> cells = tables.cellSet();
//		for (Cell<String, BigDecimal, String> temp : cells) {
//			System.out.println(temp.getRowKey() + " " + temp.getColumnKey() + " " + temp.getValue());
//		}

	}

	public static List<Order> createOrder() {
		List<Order> orders = Lists.newArrayList();
		orders.add(new Order("001", new BigDecimal(10), "hha"));
		orders.add(new Order("002", new BigDecimal(20), "hha"));
		orders.add(new Order("003", new BigDecimal(30), "hha"));
		orders.add(new Order("004", new BigDecimal(40), "hha"));
		orders.add(new Order("005", new BigDecimal(50), "hha"));
		orders.add(new Order("006", new BigDecimal(60), "hha"));
		orders.add(new Order("007", new BigDecimal(70), "hha"));
		orders.add(new Order("008", new BigDecimal(80), "hha"));
		orders.add(new Order("009", new BigDecimal(90), "hha"));
		orders.add(new Order("010", new BigDecimal(100), "hha"));
		return orders;
	}

	public static class Order {
		private String id;
		private BigDecimal amt;
		private String remark;

		public Order() {
		}

		public Order(String id, BigDecimal amt, String remark) {
			this.id = id;
			this.amt = amt;
			this.remark = remark;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public BigDecimal getAmt() {
			return amt;
		}

		public void setAmt(BigDecimal amt) {
			this.amt = amt;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

	}

}
