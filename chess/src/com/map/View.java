package com.map;

import java.util.ArrayList;
import java.util.List;

import com.chess.Chess;

public class View {
	private List<Chess> chess = new ArrayList();
	
	public View(){
		
	}
	
	public void addChess(Chess chess){
		this.chess.add(chess);
	}

	public List<Chess> getChess() {
		return chess;
	}	
}
