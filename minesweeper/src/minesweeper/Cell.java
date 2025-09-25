package minesweeper;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Cell extends JButton {
	   private static final long serialVersionUID = 1L;  // to prevent serial warning

	   // Define named constants for JButton's colors and fonts
	   //  to be chosen based on cell's state
	   public static final Color BG_NOT_REVEALED = Color.GREEN;
	   public static final Color FG_NOT_REVEALED = Color.RED;    // flag, mines
	   public static final Color BG_REVEALED = Color.DARK_GRAY;
	   public static final Color FG_REVEALED = Color.YELLOW; // number of mines
	   public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	   // Define properties (package-visible)
	   /** The row and column number of the cell */
	   int row, col;
	   /** Already revealed? */
	   boolean isRevealed;
	   /** Is a mine? */
	   boolean isMined;
	   /** Is Flagged by player? */
	   boolean isFlagged;
	   int surroundingMines;
	   GameBoardPanel board;

	   /** Constructor */
	   public Cell(int row, int col, GameBoardPanel board) {
		      super();
		      this.row = row;
		      this.col = col;
		      this.board = board;
		      super.setFont(FONT_NUMBERS);
		   }

	   /** Reset this cell, ready for a new game */
	   public void newGame(boolean isMined) {
		      this.isRevealed = false;
		      this.isFlagged = false;
		      this.isMined = isMined;
		      this.surroundingMines = 0;
		      super.setEnabled(true);
		      super.setText("");
		      paint();
		  }

	   public void setSurroundingMines(int count) {
		      this.surroundingMines = count;
		   }
	   
	   public void reveal() {
		      if (isRevealed) return;
		      isRevealed = true;
		      setEnabled(false);
		      paint();

		      // ถ้าไม่มีระเบิดรอบ ๆ ให้เปิด cell รอบ ๆ
		      if (!isMined && surroundingMines == 0) {
		         board.revealSurrounding(row, col);
		      }
		   }
	   
	   public void toggleFlag() {
		      if (isRevealed) return;
		      isFlagged = !isFlagged;
		      paint();
		   }

		/** ให้ cell รู้จัก board ของมัน */
	   public GameBoardPanel getBoard() {
		   	return board;
	   }
	   
	   /** Paint itself based on its status */
	   public void paint() {
		      if (!isRevealed) {
		         // ยังไม่เปิด
		         if (isFlagged) {
		            super.setText("F");  // แสดงธง
		         } else {
		            super.setText("");
		         }
		         super.setBackground(BG_NOT_REVEALED);
		         super.setForeground(FG_NOT_REVEALED);
		      } else {
		         // ถ้าเปิดแล้ว
		         if (isMined) {
		            super.setText("*"); // ระเบิด
		         } else if (surroundingMines > 0) {
		            super.setText(String.valueOf(surroundingMines));
		         } else {
		            super.setText("");
		         }
		         super.setBackground(BG_REVEALED);
		         super.setForeground(FG_REVEALED);
		      }
		   }
	}
