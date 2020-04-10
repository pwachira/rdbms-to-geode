/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springoneplatform.scs.demo.model.pdx;

import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.List;
import java.util.Set;

@Region("customerOrder")
public class CustomerOrder {

	private String customerId;
	private String shippingAddress;
	private long orderDate;
	private List<String> itemSet;

	public CustomerOrder() {
		super();
	}

	public CustomerOrder(String customerId, String shippingAddress, long orderDate, List<String> itemSet) {
		super();
		this.customerId = customerId;
		this.shippingAddress = shippingAddress;
		this.orderDate = orderDate;
		this.itemSet = itemSet;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

	public List<String> getItemSet() {
		return itemSet;
	}

	public void setItemSet(List<String> itemSet) {
		this.itemSet = itemSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((itemSet == null) ? 0 : itemSet.hashCode());
		result = prime * result + (int) (orderDate ^ (orderDate >>> 32));
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrder other = (CustomerOrder) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (itemSet == null) {
			if (other.itemSet != null)
				return false;
		} else if (!itemSet.equals(other.itemSet))
			return false;
		if (orderDate != other.orderDate)
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerOrder [customerId=" + customerId + ", shippingAddress=" + shippingAddress + ", orderDate="
				+ orderDate + ", itemSet=" + itemSet + "]";
	}

}
