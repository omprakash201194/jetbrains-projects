function sum(numbers) {
    // write your code here
    let sum = 0;
    for (i in numbers) {
        if (numbers[i] === 0) {
            break;
        }
        sum = sum + numbers[i];
    }
    return sum;
}
