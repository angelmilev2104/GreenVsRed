import java.util.Scanner;

public class GreenVsRed {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter x, y: ");

        String input = scanner.nextLine();

        String[] grid = input.split(", ");
        int cell = Integer.parseInt(grid[0]);
        int row = Integer.parseInt(grid[1]);

        int[][] actualGeneration = new int[cell][row];

        for (int i = 0; i < row; i++) {
            System.out.printf("Enter row %d: ", i + 1);
            input = scanner.nextLine();
            for (int j = 0; j < cell; j++) {
                actualGeneration[i][j] = Integer.parseInt(input.substring(j, j + 1));
            }
        }

        System.out.print("Define the observed cell and n: ");
        input = scanner.nextLine();
        String[] observer = input.split(", ");
        int c = Integer.parseInt(observer[0]);
        int r = Integer.parseInt(observer[1]);
        int n = Integer.parseInt(observer[2]);


        int count = 0;

        for (int y = 0; y < n; y++) {
            int[][] nextGeneration = new int[cell][row];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < cell; j++) {
                    nextGeneration[i][j] = countNeighbours(actualGeneration, i, j, actualGeneration[i][j]);
                }
            }

            if (nextGeneration[c][r] == 1) {
                count++;
            }

            actualGeneration = nextGeneration;
        }

        System.out.printf("Result is: %d", count);

    }

    private static int countNeighbours(int[][] actualG, int x, int y, int color) {

        int greenCount = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i == x && j == y)) {
                    if (validIndex(i, actualG.length)) {
                        if (validIndex(j, actualG[i].length)) {
                            if (actualG[i][j] == 1) {
                                greenCount++;
                            }
                        }
                    }
                }

            }
        }

        if (color == 0) {
            switch (greenCount) {
                case 3:
                case 6:
                    return 1;
                default:
                    return 0;
            }
        } else {
            switch (greenCount) {
                case 2:
                case 3:
                case 6:
                    return 1;
                default:
                    return 0;

            }
        }

    }

    private static boolean validIndex(int x, int length) {
        if (x >= 0 && x < length) {
            return true;
        }
        return false;
    }
}
