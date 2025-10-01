package minesweeper;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import static minesweeper.MineSweeperConstants.ROWS;
import static minesweeper.MineSweeperConstants.COLS;

public class GameBoardPanel extends JPanel {
	   private static final long serialVersionUID = 1L;

	   // UI sizes
	   public static final int CELL_SIZE = 60;
	   public static final int CANVAS_WIDTH  = CELL_SIZE * COLS;
	   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

	   // Properties
	   Cell[][] cells = new Cell[ROWS][COLS];
	   int numMines = 10;
	   

	   /** Constructor */
	   public GameBoardPanel() {
	      super.setLayout(new GridLayout(ROWS, COLS, 2, 2));
	      // Listener สำหรับทุก cell
	      CellMouseListener listener = new CellMouseListener();

	      // สร้าง cell ใส่ listener
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            cells[row][col] = new Cell(row, col, this);
	            cells[row][col].addMouseListener(listener);
	            super.add(cells[row][col]);
	         }
	      }

	      super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

	      newGame(); // เริ่มเกมแรก
	   }

	   public void revealSurrounding(int row, int col) {
	        // loop 8 ทิศรอบ cell
	        for (int dr = -1; dr <= 1; dr++) {
	            for (int dc = -1; dc <= 1; dc++) {
	                if (dr == 0 && dc == 0) continue;

	                int newRow = row + dr;
	                int newCol = col + dc;

	                if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS) {
	                    Cell neighbor = cells[newRow][newCol];
	                    if (!neighbor.isRevealed && !neighbor.isMined) {
	                        neighbor.reveal(); // เปิด cell ข้างเคียง
	                    }
	                }
	            }
	        }
	    }
	   
	   /** เริ่มเกมใหม่ */
	   public void newGame() {
	        // เคลียร์ cell เก่า
	        for (int r = 0; r < ROWS ; r++) {
	            for (int c = 0; c < COLS; c++) {
	                cells[r][c].reset();
	            }
	        }

	        // สุ่มวาง mine
	        Random rand = new Random();
	        int minesPlaced = 0;
	        while (minesPlaced < 10) {
	            int r = rand.nextInt(ROWS);
	            int c = rand.nextInt(COLS);

	            if (!cells[r][c].isMined) {
	                cells[r][c].setMine(true);
	                minesPlaced++;
	            }
	        }

	        // คำนวณ mine รอบๆ แต่ละ cell
	        for (int r = 0; r < ROWS; r++) {
	            for (int c = 0; c < COLS; c++) {
	                int count = getSurroundingMines(r, c);
	                cells[r][c].setSurroundingMines(count);
	            }
	        }
	    }

	   /** นับจำนวนระเบิดรอบ cell */
	   private int getSurroundingMines(int srcRow, int srcCol) {
	      int count = 0;
	      for (int r = srcRow - 1; r <= srcRow + 1; r++) {
	         for (int c = srcCol - 1; c <= srcCol + 1; c++) {
	            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
	               if (cells[r][c].isMined) count++;
	            }
	         }
	      }
	      return count;
	   }

	   /** เปิด cell */
	   public void revealCell(int row, int col) {
	      if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;
	      Cell cell = cells[row][col];
	      if (cell.isRevealed || cell.isFlagged) return;

	      cell.reveal();

	      // ถ้าไม่มีระเบิดรอบ ๆ → เปิดรอบข้างต่อ
	      if (!cell.isMined && cell.surroundingMines == 0) {
	         for (int dr = -1; dr <= 1; dr++) {
	            for (int dc = -1; dc <= 1; dc++) {
	               if (!(dr == 0 && dc == 0)) {
	                  revealCell(row + dr, col + dc);
	               }
	            }
	         }
	      }
	   }

	   /** ตรวจว่าชนะหรือยัง */
	   public boolean hasWon() {
	      for (int r = 0; r < ROWS; r++) {
	         for (int c = 0; c < COLS; c++) {
	            Cell cell = cells[r][c];
	            if (!cell.isMined && !cell.isRevealed) {
	               return false; // ยังมีช่องที่ไม่เปิด
	            }
	         }
	      }
	      return true;
	   }

	   /** Listener คลิกซ้าย/ขวา */
	   private class CellMouseListener extends MouseAdapter {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	         Cell cell = (Cell) e.getSource();

	         // คลิกซ้าย
	         if (e.getButton() == MouseEvent.BUTTON1) {
	            if (cell.isMined) {
	               cell.reveal();
	               JOptionPane.showMessageDialog(GameBoardPanel.this, "Game Over!");
	               newGame();
	            } else {
	               revealCell(cell.row, cell.col);
	               if (hasWon()) {
	                  JOptionPane.showMessageDialog(GameBoardPanel.this, "You Win!");
	                  newGame();
	               }
	            }
	         }

	         // คลิกขวา → toggle flag
	         else if (e.getButton() == MouseEvent.BUTTON3) {
	            cell.toggleFlag();
	         }
	      }
	   }
}