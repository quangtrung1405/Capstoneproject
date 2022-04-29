package com.hcl.shopforhome.service;

import com.hcl.shopforhome.bean.AdminDetails;

public interface IAdminService {

	public String signUp(AdminDetails admin);

	public String signIn(AdminDetails admin);
}
