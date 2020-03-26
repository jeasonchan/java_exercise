package default_package.车的可用捕获量;

public class Main {
    public int numRookCaptures(char[][] board) {
        final char Rook = 'R';//自身
        final char Blank = '.';
        final char Bishop = 'B';
        final char Pawn = 'p';//目标

        final int i_length = 8;
        final int j_length = 8;

        int i_Rook = 0;
        int j_Rook = 0;
        int result = 0;

        for (int i = 0; i < i_length; i++) {
            for (int j = 0; j < j_length; j++) {
                if (Rook == (board[i][j])) {
                    i_Rook = i;
                    j_Rook = j;
                    break;
                }
            }
        }

        final int[] i_div = new int[]{0, 0, 1, -1};
        final int[] j_div = new int[]{1, -1, 0, 0};

        for (int direction = 0; direction < 4; direction++) {
            int init_i_div = i_div[direction];
            int init_j_div = j_div[direction];
            int move = 1;


            while (i_Rook + move * init_i_div >= 0 && i_Rook + move * init_i_div < 8 &&
                    j_Rook + move * init_j_div >= 0 && j_Rook + move * init_j_div < 8) {

                System.out.println(board[i_Rook + move * init_i_div][j_Rook + move * init_j_div]);

                //直到遇到该方向上的第一个Pawn
                if (Blank != board[i_Rook + move * init_i_div][j_Rook + move * init_j_div]) {
                    result += Pawn == board[i_Rook + move * init_i_div][j_Rook + move * init_j_div] ? 1 : 0;
                    break;
                }

                move++;//在同一方向上继续移动


            }
        }


        return result;

    }
}
