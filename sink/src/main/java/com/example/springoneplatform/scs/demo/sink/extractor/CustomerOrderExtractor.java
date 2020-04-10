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

package com.example.springoneplatform.scs.demo.sink.extractor;


import com.example.springoneplatform.scs.demo.model.etl.PayloadWrapper;
import com.example.springoneplatform.scs.demo.model.pdx.CustomerOrder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jeff Cherng
 */
public class CustomerOrderExtractor implements PayloadWrapperExtractor<Map, CustomerOrder> {

	@Override
	public CustomerOrder extractData(PayloadWrapper<Map> payloadWrapper) {
		CustomerOrder value = null;
		if (payloadWrapper.hasPayload()) {
			value = new CustomerOrder();
			Map payload = payloadWrapper.getPayload();
			value.setCustomerId((String) payload.get("customerId"));
			value.setShippingAddress((String) payload.get("shippingAddress"));
			value.setOrderDate((Long) payload.get("orderDate"));
			value.setItemSet((List<String>) payload.get("itemSet"));
		}
		return value;
	}

}
