package chapter2.agent_AB_v2.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import chapter2.agent_AB_v2.model.Environment.LocationState;
import chapter2.agent_AB_v2.model.EnvironmentState;
import chapter2.agent_AB_v2.model.Position;

public class Floor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LocationState[][] floorMatrix = null;
	boolean isHaveAgent;
	private int cellHeight;
	private int cellWidth;
	private Position agentLocation;

	// images
	BufferedImage agentImg, dirtImg, rockImg;
	Image background;

	public Floor() {
		try {
			agentImg = ImageIO.read(getClass().getResource("agent.png"));
			dirtImg = ImageIO.read(getClass().getResource("dirty.png"));
			rockImg = ImageIO.read(getClass().getResource("rock.png"));
			background = ImageIO.read(getClass().getResource("cleancell.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	public void drawFloor(LocationState[][] locationState) {
		this.floorMatrix = locationState;

		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (this.floorMatrix != null) {
			drawCells(g);
			drawEnv(g);
			if (agentLocation != null) {
				if (floorMatrix[agentLocation.getX()][agentLocation.getY()] == LocationState.DIRTY)
					suckIt(g);
					drawAgent(g);
			}
		}

	}

	private void suckIt(Graphics g) {
		for (int i = 0; i < 360; i++) {
			
		}
		
	}

	private void drawAgent(Graphics g) {
//		g.setColor(Color.green);
		g.drawImage(agentImg, agentLocation.getY() * cellWidth, agentLocation.getX() * cellHeight, cellWidth,
				cellHeight, null);

	}

	private void drawEnv(Graphics g) {
		for (int i = 0; i < floorMatrix.length; i++) {
			for (int j = 0; j < floorMatrix[i].length; j++) {
				if (floorMatrix[i][j] == LocationState.DIRTY) {
//					g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
					g.drawImage(dirtImg, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
				} else if (floorMatrix[i][j] == LocationState.OBSTAClES) {
//					g.setColor(Color.RED);
//					g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
					g.drawImage(rockImg, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
				}
			}
		}
	}

	private void drawCells(Graphics g) {
		cellHeight = this.getHeight() / floorMatrix.length;
		cellWidth = this.getWidth() / floorMatrix[0].length;
		for (int i = 0; i <= floorMatrix.length; i++) {
			g.drawLine(i * cellWidth, 0, cellWidth * i, this.getHeight());
		}
		for (int i = 0; i <= floorMatrix[0].length; i++) {
			g.drawLine(0, cellHeight * i, this.getWidth(), cellHeight * i);

		}
	}

	public void generateAgent(Position agentLocation) {
		this.agentLocation = agentLocation;
		this.repaint();
	}

	public void updateView(EnvironmentState envState) {
		floorMatrix = envState.getFloor();
		agentLocation = envState.getAgentLocation();
		repaint();
	}

}
