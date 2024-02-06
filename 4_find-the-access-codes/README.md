# Find the Access Codes

In order to destroy Commander Lambda's LAMBCHOP doomsday device, you'll need access to it. But the only door leading to the LAMBCHOP chamber is secured with a unique lock system whose number of passcodes changes daily. Commander Lambda gets a report every day that includes the locks' access codes, but only the Commander knows how to figure out which of several lists contains the access codes. You need to find a way to determine which list contains the access codes once you're ready to go in.

Fortunately, now that you're Commander Lambda's personal assistant, Lambda has confided to you that all the access codes are "lucky triples" in order to make it easier to find them in the lists. A "lucky triple" is a tuple (x, y, z) where x divides y and y divides z, such as (1, 2, 4). With that information, you can figure out which list contains the number of access codes that matches the number of locks on the door when you're ready to go in (for example, if there's 5 passcodes, you'd need to find a list with 5 "lucky triple" access codes).

Write a function solution(l) that takes a list of positive integers l and counts the number of "lucky triples" of (li, lj, lk) where the list indices meet the requirement i < j < k. The length of l is between 2 and 2000 inclusive. The elements of l are between 1 and 999999 inclusive. The solution fits within a signed 32-bit integer. Some of the lists are purposely generated without any access codes to throw off spies, so if no triples are found, return 0.

For example, [1, 2, 3, 4, 5, 6] has the triples: [1, 2, 4], [1, 2, 6], [1, 3, 6], making the solution 3 total.

# Languages

To provide a Java solution, edit Solution.java
To provide a Python solution, edit solution.py

# Test cases

Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Java cases --
Input:
Solution.solution([1, 1, 1])
Output:
1

Input:
Solution.solution([1, 2, 3, 4, 5, 6])
Output:
3

-- Python cases --
Input:
solution.solution([1, 1, 1])
Output:
1

Input:
solution.solution([1, 2, 3, 4, 5, 6])
Output:
3

---

## Understanding the Problem

The task is to find all "lucky triples" in a given list, l. A "lucky triple" is a tuple of indices (i, j, k) such that i < j < k and l[i] divides l[j], and l[j] divides l[k].

### Approach

The solution involves two nested loops to iterate through the list and a strategy to count divisible pairs that could form part of a "lucky triple".

### Initialize Counters:

count: To keep track of the total number of "lucky triples" found.
divisors_count: A list of zeros, of the same length as l, to count how many times each element has been found to divide another element that comes after it in the list.

### Iterate Through the List:

The outer loop (i) goes through each element of the list one by one.
The inner loop (j) then iterates through the elements following i, to find elements l[j] that are divisible by l[i].

### Count Divisible Pairs and "Lucky Triples":

Whenever l[j] % l[i] == 0 (meaning l[i] divides l[j]), we know we have found a pair that could potentially be part of a "lucky triple".
Every time such a pair is found, divisors_count[j] is incremented. This increment represents the number of ways l[j] can be the middle element in a "lucky triple", given the current i.
Additionally, for each divisible pair found, count is incremented by divisors_count[i]. This step is crucial because it leverages the work done by previous iterations. If l[i] was found to divide some elements before j, then for every such element, there's a potential "lucky triple" ending with l[j]. Hence, divisors_count[i] effectively counts the number of ways l[i] has been the first element in a "lucky triple" that can now be extended to l[j].

## Example Walkthrough
Consider the list [1, 2, 3, 4, 5, 6]. The solution works as follows:

When i=0 (l[0]=1), for each subsequent j, l[j] is divisible by l[i] since 1 divides everything. divisors_count for each j greater than i is incremented.
When i=1 (l[1]=2), divisors_count[3] (for l[3]=4) and divisors_count[5] (for l[5]=6) are incremented because 2 divides 4 and 6. Meanwhile, count is increased based on previous divisor counts.
By the end of the iteration, count captures the total number of "lucky triples".

## Why This Approach Works
This algorithm efficiently captures the essence of the problem by breaking it down into manageable parts—first finding divisible pairs and then linking them together to form "lucky triples". It avoids unnecessary recomputation by keeping track of intermediate results in divisors_count, making the solution both elegant and efficient for the problem's constraints.
