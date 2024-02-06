def solution(l):
    count = 0
    n = len(l)
    divisors_count = [0] * n  # Tracks count of divisors for each element

    # Iterate through each element to find divisible pairs
    for i in range(n):
        for j in range(i + 1, n):
            if l[j] % l[i] == 0:  # If l[i] divides l[j]
                divisors_count[j] += 1  # Increment divisor count for l[j]
                count += divisors_count[i]  # Add the count of divisors for l[i] to total

    return count
