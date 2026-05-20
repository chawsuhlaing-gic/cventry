package mm.com.gic.ces.application.model;

import java.util.List;

public class Pagination<T> {
    private int selectedPageNumber;          //選択したページ番号
    private int totalPages;                  //総ページ
    private int serialNumberAddfactor;
    private List<T> tList;                   //リスト

    //選択したページ番号
	public int getSelectedPageNumber() {
		return selectedPageNumber;
	}

	public void setSelectedPageNumber(int selectedPageNumber) {
		this.selectedPageNumber = selectedPageNumber;
	}

	//総ページ
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getSerialNumberAddfactor() {
		return serialNumberAddfactor;
	}

	public void setSerialNumberAddfactor(int serialNumberAddfactor) {
		this.serialNumberAddfactor = serialNumberAddfactor;
	}

	//リスト
	public List<T> getTList() {
		return tList;
	}

	public void setTList(List<T> tList) {
		this.tList = tList;
	}
}