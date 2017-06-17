package common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DepartmentBean;
import beans.DepartmentSectionBean;
import beans.PositionBean;
import department.DepartmentSectionKanri;
import position.PositionKanri;

/**
 * オプションメニューを生成するクラス
 *
 * @author ohs55018
 *
 */
public class OptionMenuCreate {


	/**
	 * 部署一覧のoptionメニューを作成するメソッド
	 *
	 * @param deptId チェックされているdeptIdを受け取る
	 * @return
	 */
	public static String deptOptionMenuCreate(String deptId){

		List<DepartmentBean> departmentBeanList = new ArrayList<DepartmentBean>();
		DepartmentSectionKanri dsKanri = new DepartmentSectionKanri();
		try {
			departmentBeanList = dsKanri.allDeptSearch();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		StringBuilder sb2 = new StringBuilder(300);
		String selected;
		for (int i = 0; i < departmentBeanList.size(); i++) {
			if(deptId.equals(departmentBeanList.get(i).getDeptId())){
				selected="selected";
			}else{
				selected="";
			}
			sb2.append("<option value='" + departmentBeanList.get(i).getDeptId()+","+departmentBeanList.get(i).getDeptName()+ "'"+selected+">");
			sb2.append(departmentBeanList.get(i).getDeptName() + "</option>");
		}
		String strDepartmentBeanList = sb2.toString();

		return strDepartmentBeanList;
	}

	/**
	 * 役職一覧のoptionメニューを作成するメソッド
	 *
	 * @param positionId チェックされているpositionIdを受け取る
	 * @return
	 */


	public static String positionOptionMenuCreate(String positionId){

		List<PositionBean> positionBeanList = new ArrayList<PositionBean>();
		PositionKanri psKanri = new PositionKanri();
		try {
			positionBeanList = psKanri.getAllPosition();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		StringBuilder sb2 = new StringBuilder(300);
		String selected;
		for (int i = 0; i < positionBeanList.size(); i++) {
			if(positionId.equals(positionBeanList.get(i).getPositionId())){
				selected="selected";
			}else{
				selected="";
			}
			sb2.append("<option value='" + positionBeanList.get(i).getPositionId()+","+positionBeanList.get(i).getPositionName()+ "'"+selected+">");
			sb2.append(positionBeanList.get(i).getPositionName() + "</option>");
		}
		String strPositionBeanList = sb2.toString();

		return strPositionBeanList;
	}

	/**
	 * 選択された部署の課のoptionメニューを作成するメソッド
	 *
	 * @param deptId 部署を識別するID
	 * @return
	 */

	public static String sectionOptionMenuCreate(String deptId){
		List<DepartmentSectionBean> list = new ArrayList<DepartmentSectionBean>();
		DepartmentSectionKanri dsKanri = new DepartmentSectionKanri();
		try {
			list = dsKanri.deptSectionSearch(deptId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		StringBuilder sb2 = new StringBuilder(300);
		for (int i = 0; i < list.size(); i++) {
			String sectionName = list.get(i).getSectionName();
			if(sectionName==null||sectionName.equals("")){
				sectionName="所属なし";
			}

			sb2.append("<option value='" + list.get(i).getSectionId()+","+sectionName+ "'>");
			sb2.append(sectionName + "</option>");
		}


		return sb2.toString();
	}

	/**
	 * 選択された部署の課のoptionメニューを作成するメソッド
	 * @param deptId 部署を識別するID
	 * @param sectionId チェックされている課の場所を識別するID
	 * @return
	 */

	public static String sectionOptionMenuCreate(String deptId,String sectionId){
		List<DepartmentSectionBean> list = new ArrayList<DepartmentSectionBean>();
		DepartmentSectionKanri dsKanri = new DepartmentSectionKanri();
		try {
			list = dsKanri.deptSectionSearch(deptId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		StringBuilder sb2 = new StringBuilder(300);

		String selected;
		for (int i = 0; i < list.size(); i++) {
			String sectionName = list.get(i).getSectionName();
			if(sectionName==null||sectionName.equals("")){
				sectionName="所属なし";
			}


			if(sectionId.equals(list.get(i).getSectionId())){
				selected="selected";
			}else{
				selected="";
			}

			sb2.append("<option value='" + list.get(i).getSectionId()+","+sectionName+ "' "+selected+">");
			sb2.append(sectionName + "</option>");
		}


		return sb2.toString();
	}




}
