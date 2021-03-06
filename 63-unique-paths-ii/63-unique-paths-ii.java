class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // return f(obstacleGrid, obstacleGrid.length-1, obstacleGrid[0].length-1);
        
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        
        // int ans = pathCount(obstacleGrid, m-1, n-1, dp);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }
        int ans = tabulated(obstacleGrid, dp, m, n);
        return ans;
        // return spaceOptimised(m, n, obstacleGrid);
    }
    
    private static int spaceOptimised(int row, int col, int[][] maze) {
        int[] prev = new int[col];

        for(int r = 0; r<row; r++) {

            int[] temp = new int[col];

            for(int c = 0; c < col; c++) {

                // base case
                if(maze[r][c] == 1) {
                    temp[c]=0;
                    continue;
                }

                if(r==0 && c==0){
                    temp[c]=1;
                    continue;
                }

                // recursive case
                int up=0;
                int left =0;

                if(r>0) up = prev[c];

                if(c>0) left = temp[c-1];

                temp[c] = up + left;
            }
            // System.out.println(Arrays.toString(prev));
            prev = temp;

        }

        return prev[row-1];
    }
    
    private static int tabulated(int[][] maze, int[][] dp, int m, int n) {
        
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {

                // base case
                if(maze[r][c] == 1){
                    dp[r][c] = 0;
                    continue;
                }
                if(r==0 && c==0){
                    dp[r][c] = 1;
                    continue;
                }

                // recursive case
                int down = 0;
                int right = 0;

                if(r > 0) {
                    down = dp[r - 1][c];
                }

                if(c > 0) {
                    right = dp[r][c - 1];
                }

                dp[r][c] = down + right;
            }
        }

        return dp[m-1][n-1];
    }
    
    private static int pathCount(int[][] maze, int r, int c, int[][] dp) {
        
        if(r < 0 || c < 0) {
            return 0;
        }
        
        if((r>0 || c>0) && maze[r][c] == 1) {
            return 0;
        }

        if(r == 0 && c == 0 && maze[r][c] != 1) {
            return 1;
        }

        if(dp[r][c] != -1) return dp[r][c];


        int down = pathCount(maze, r - 1, c, dp);

        int right = pathCount(maze, r, c - 1, dp);

        dp[r][c] = down + right;
        return dp[r][c];
    
    }
}