class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort( properties,(a,b)-> ( a[0] == b[0] ) ? a[1]-b[1] : b[0]-a[0] );
        int max = -1,result = 0;
        for(int[] property: properties){
            if(property[1] < max) 
				result++;
            else 
				max = property[1];
        }
        return result;
    }
}