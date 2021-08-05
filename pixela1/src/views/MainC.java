package views;

import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import application.MainGame;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainC{
	@FXML
	public Canvas c;

	@FXML
	public ImageView imgv;

	@FXML
	public ColorPicker cp;
	public Color cl;

	private MainGame game;

	private Stage mainStage;

	private GraphicsContext gc;

	@FXML
	private RadioButton drow;

	@FXML
	private RadioButton remove;

	@FXML
	private CheckBox drag;
	
	private double beforeX;
	private double beforeY;
	
	public void setStage(Stage stage) {
		mainStage = stage;
	}

	@FXML
	private void initialize() {
		gc = c.getGraphicsContext2D();
		game = new MainGame(gc);
		game.render();
	}

	public Color cl1() {
		cl = cp.getValue();
		return cl;
	}

	public void save() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.setTitle("이미지 파일 선택");

		ExtensionFilter imgFilter = new ExtensionFilter("image file", "*.png");

		chooser.getExtensionFilters().add(imgFilter);

		File file = chooser.showSaveDialog(mainStage);

		if (file != null) {
			try {
				WritableImage wImage = new WritableImage((int) c.getWidth(), (int) c.getHeight());
				c.snapshot(null, wImage);

				RenderedImage rImage = SwingFXUtils.fromFXImage(wImage, null);
				ImageIO.write(rImage, "png", file);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일 저장중 오류 발생");
			}
		}
	}

	public void load() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.setTitle("이미지 파일 선택");

		ExtensionFilter imgFilter = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");

		chooser.getExtensionFilters().add(imgFilter);

		File file = chooser.showOpenDialog(mainStage);

		if (file != null) {
			try {
				String fileset = file.toString();
				FileInputStream fis = new FileInputStream(fileset);
				BufferedInputStream bis = new BufferedInputStream(fis);
				Image img = new Image(bis);
				imgv.setImage(img);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일 불러오는중 오류 발생");
			}
		}
	}
	public void cload() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.setTitle("이미지 파일 선택");

		ExtensionFilter imgFilter = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");

		chooser.getExtensionFilters().add(imgFilter);

		File file = chooser.showOpenDialog(mainStage);

		if (file != null) {
			try {
				String fileset = file.toString();
				FileInputStream fis = new FileInputStream(fileset);
				BufferedInputStream bis = new BufferedInputStream(fis);
				Image img = new Image(bis);
				double w = img.getWidth();
				double h = img.getHeight();
				System.out.println(w +", " + h);
				double cw = c.getWidth();
				double ch = c.getHeight();
				gc.drawImage(img, 0, 0, cw, ch);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일 불러오는중 오류 발생");
			}
		}
	}

	public void allremove() {
		gc.clearRect(0, 0, 375, 375);
		game.render();
	}
	
	public void clickHandler(MouseEvent e) {
		beforeX = e.getX();
		beforeY = e.getY();
	if (drow.isSelected() && !drag.isSelected()) {
			game.mouseClick(e, cl);
		}
		if (remove.isSelected() && !drag.isSelected()) {
			game.mouseRemove(e);
		}
	}
	
	public void dHandler(MouseEvent e) {
		if(drow.isSelected() && drag.isSelected()) {
			game.mouseClick(e, cl);
		}
		if(remove.isSelected() && drag.isSelected()) {
			game.mouseRemove(e);
		}
	}
	
}
