function checkEvenOrOdd(numbers) {
    // write your code here
    for (i in numbers) {
        if (numbers[i] === 0) {
            break;
        } else if (numbers[i] % 2 === 0) {
            console.log("even");
        }else {
            console.log("odd");
        }
    }
}