package com.board.web.serviceImpl;

import com.board.web.service.PaginationService;

public class PaginationServiceImpl implements PaginationService {

	@Override
	public int[] calculateRows(String[] params) {
		int [] rowValues =new int [7];
		int pageNumber =Integer.parseInt(params[0]);
		int theNumberOfRows=Integer.parseInt(params[1]);
		int rowsPerPage=Integer.parseInt(params[2]);
		int pagesPerBlock= Integer.parseInt(params[3]);
		int theNumberOfPages=(theNumberOfRows% rowsPerPage == 0)? theNumberOfRows/rowsPerPage:theNumberOfRows/rowsPerPage +1,
			startPage = pageNumber -((pageNumber-1)%pagesPerBlock),
			endPage=((startPage + rowsPerPage -1)<theNumberOfPages)?startPage+pagesPerBlock-1:theNumberOfPages,
			startRow=(pageNumber-1)*rowsPerPage+1,
			endRow=pageNumber*rowsPerPage,
			prevBlock= startPage- pagesPerBlock,
			nextBlock=startPage+rowsPerPage;
		rowValues[0]=startRow;
		rowValues[1]=endRow;
		rowValues[2]=startPage;
		rowValues[3]=endPage;
		rowValues[4]=prevBlock;
		rowValues[5]=nextBlock;
		rowValues[6]=theNumberOfPages;
		return rowValues;
	}
}
