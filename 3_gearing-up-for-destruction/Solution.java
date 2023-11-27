public class Solution {
    public static int[] solution(int[] pegs) {
        /* r1 will contain the numerator and denominator of the radius of the first gear, also
         * there are always at least two pegs, so we can already compute S_2 and save it in r1[0]. */
        int[] r1 = {pegs[1] - pegs[0], 1};
        int min_s_even = r1[0]; //Initialize with S_2, which is the smallest Sn_even known so far
        int max_s_odd = 0;      //Initialize with S_1 (defined as 0), which is the biggest Sn_odd known so far

        //This cycle is the implementation of the summation according to the formula
        for(int i = 2; i < pegs.length; i++) {
            //Note that here 'i' is the index of the array "pegs", while in the formula it's the number of the peg (starting from 1)
            if(i % 2 == 0) {
                r1[0] += -pegs[i] + pegs[i-1]; //Note that r1[0] will contain odd S_N whenever 'i' is even
                if(r1[0] > max_s_odd) { //Find max(Sn_odd)
                    max_s_odd = r1[0];
                }
            } else {
                r1[0] += pegs[i] - pegs[i-1]; //Note that r1[0] will contain even S_N whenever 'i' is odd
                if(r1[0] < min_s_even) { //Find min(Sn_even)
                    min_s_even = r1[0];
                }
            }
        }
        //We multiply the result of the summation by 2 or 2/3, depending on whether the number of pegs is odd or even
        r1[0] *= 2;
        if(pegs.length % 2 == 0) {
            //If possible we simplify the fraction (as required by the challenge)
            if(r1[0] % 3 == 0) {
                r1[0] /= 3;
            } else {
                r1[1] = 3;
            }
        }
        //Verify that no radius is less than 1
        if(r1[0] < (max_s_odd + 1) * r1[1] || r1[0] > (min_s_even - 1) * r1[1]) {
            r1[0] = r1[1] = -1;
        }

        return r1;
    }
}
