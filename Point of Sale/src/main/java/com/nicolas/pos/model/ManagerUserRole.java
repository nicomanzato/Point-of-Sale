package com.nicolas.pos.model;

import javax.persistence.*;

@Entity  
@DiscriminatorValue("Manager")
public class ManagerUserRole extends UserRole{

	@Override
	public boolean canCreateProduct() {
		return true;
	}

	@Override
	public boolean canDeleteProduct() {
		return true;
	}

	@Override
	public boolean canUpdateProduct() {
		return true;
	}

	@Override
	public boolean canCreateOrder() {
		return true;
	}

	@Override
	public boolean canDeleteOrder() {
		return true;
	}

	@Override
	public boolean canUpdateOrder() {
		return true;
	}

	@Override
	public boolean canCreateUser() {
		return true;
	}

	@Override
	public boolean canAccessAllOrders() {
		return true;
	}

	
}
