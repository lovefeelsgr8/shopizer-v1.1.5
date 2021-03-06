/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 25, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.PrincipalProxy;

import com.opensymphony.xwork2.ActionInvocation;
import com.salesmanager.common.ShopInterceptor;
import com.salesmanager.core.util.www.BaseAction;

public class ProfileInterceptor extends ShopInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invoke,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		req.getSession().removeAttribute("mainUrl");
		req.getSession().removeAttribute("subCategory");
		req.getSession().removeAttribute("categoryPath");
		req.getSession().removeAttribute("IDLIST");
		req.getSession().removeAttribute("CATEGORYPATH");

		req.getSession().setAttribute("profileUrl", "profile");

		Object o = invoke.getAction();
		if (o instanceof BaseAction) {
			BaseAction action = ((BaseAction) invoke.getAction());
			PrincipalProxy principal = action.getPrincipal();

			if (principal == null || principal.getRemoteUser() == null) {
				return "AUTHORIZATIONERROR";
			}
		}

		return null;

	}

}
