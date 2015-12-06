package org.module.client.businesslogic.orderbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.module.client.businesslogicservice.order.OfficeArrivalService;
import org.module.client.javaRMI.RmiClient;
import org.module.client.vo.OfficeArrivalListVO;
import org.module.common.dataservice.orderdataservice.OfficeArrivalListService;
import org.module.common.po.OfficeArrivalListPO;

public class OfficeArrival implements OfficeArrivalService {

	private OfficeArrivalListService officeArrivalData ;
	public OfficeArrival() {
		officeArrivalData = new RmiClient().get(OfficeArrivalListService.class);
	}

	public boolean creat(OfficeArrivalListVO o) {
		OfficeArrivalListPO newPO = new OfficeArrivalListPO(o.getDepartmentId(),o.getDate(),
				o.getTransportListId(),o.getOrigin(),o.getStateOfGoods(),o.getState()); 
		try {
			return officeArrivalData.add(newPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<OfficeArrivalListVO> getAll() {
		ArrayList<OfficeArrivalListVO> newVOs = new ArrayList<OfficeArrivalListVO>();
		ArrayList<OfficeArrivalListPO> POs = null;
		try {
			 POs = officeArrivalData.getAll();
			 for(int i =0;i<POs.size();i++){
			newVOs.add(new OfficeArrivalListVO(POs.get(i).getOfficeid(),POs.get(i).getDate(),
					POs.get(i).getTransportListId(),POs.get(i).getOrigin(),POs.get(i).getStateOfGoods(),POs.get(i).getState()));
		}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return newVOs;
	}

	public boolean update(OfficeArrivalListVO o){
		OfficeArrivalListPO newPO = new OfficeArrivalListPO(o.getDepartmentId(),o.getDate(),
				o.getTransportListId(),o.getOrigin(),o.getStateOfGoods(),o.getState()); 
		try {
			return officeArrivalData.update(newPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}
}
