package application;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import views.MainC;

public class MainGame {
	private GraphicsContext gc;
	private int gap = 0; //상자 사이의 거리
	private int size = 15; //상자 사이즈
	
	private Integer[][] board;
	
	public MainGame(GraphicsContext gc) {
		this.gc =gc;
		board = new Integer[25][25];
	}
	
	
	public void render() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				int x = gap + j *(gap + size);
				int y = gap + i *(gap + size);
				
				gc.setFill(Color.rgb(194, 192, 190));
				gc.fillRect(x, y, size, size);
				
				gc.setFill(Color.WHITE);
				gc.fillRect(x + 1, y + 1, size -2, size-2);
			}
		}
	}


	public void mouseClick(MouseEvent e,Color cl) {
		double x = e.getX();
		double y = e.getY();
		//x가 1일경우 y에만 15를 곱해주고, y가 1일경우 x에만 15를 곱해준다 
		//둘다 1이아닐경우 양쪽다 15를곱해주고 , 둘다 1일경우 양쪽다 15를 곱하지않는다
		if (x % (gap + size) < gap || y % (gap + size) < gap) {
			return;
		}
		int i = (int)(y / (gap + size));
		int j = (int)(x / (gap+ size));
		if (i == 0 && j == 0) {
			gc.setFill(cl);
			gc.fillRect(j, i, size, size);
		}else if(i == 0 && j != 0){
			gc.setFill(cl);
			gc.fillRect((j * 15), i, size, size);
		}else if(i != 0 && j== 0) {
			gc.setFill(cl);
			gc.fillRect(j, (i * 15), size, size);
		}else {
			gc.setFill(cl);
			gc.fillRect((j * 15), (i * 15), size, size);
		}
	}
	public void mouseRemove(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		
		int i = (int) (y / 15);
		int j = (int) (x / 15);
	
		if (i == 0 && j == 0) {
			gc.setFill(Color.rgb(194, 192, 190));
			gc.fillRect(j, i, 15, 15);

			gc.setFill(Color.WHITE);
			gc.fillRect(j + 1, i + 1, 15 - 2, 15 - 2);
		} else if (i == 0 && j != 0) {
			gc.setFill(Color.rgb(194, 192, 190));
			gc.fillRect((j * 15), i, 15, 15);

			gc.setFill(Color.WHITE);
			gc.fillRect((j * 15) + 1, i + 1, 15 - 2, 15 - 2);
		} else if (i != 0 && j == 0) {
			gc.setFill(Color.rgb(194, 192, 190));
			gc.fillRect(j, (i * 15), 15, 15);

			gc.setFill(Color.WHITE);
			gc.fillRect(j + 1, (i * 15) + 1, 15 - 2, 15 - 2);
		} else {
			gc.setFill(Color.rgb(194, 192, 190));
			gc.fillRect((j * 15), (i * 15), 15, 15);

			gc.setFill(Color.WHITE);
			gc.fillRect((j * 15) + 1, (i * 15) + 1, 15 - 2,  15 - 2);
		}
	}
}