package com.nicolas.pos.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity  
@DiscriminatorValue("Cashier") 
public class CashierUserRole extends UserRole{

	@Override
	public boolean canCreateProduct() {
		return false;
	}

	@Override
	public boolean canDeleteProduct() {
		return false;
	}

	@Override
	public boolean canUpdateProduct() {
		return false;
	}

	@Override
	public boolean canCreateOrder() {
		return true;
	}

	@Override
	public boolean canDeleteOrder() {
		return false;
	}

	@Override
	public boolean canUpdateOrder() {
		return false;
	}

	@Override
	public boolean canCreateUser() {
		return false;
	}

	

}
