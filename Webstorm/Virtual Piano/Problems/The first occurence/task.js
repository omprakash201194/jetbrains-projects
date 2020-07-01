function find5(numbers) {
    // change it
    let index = -1;
    for (i in numbers) {
        if (numbers[i] === 5) {
            index = i;
            break;
        }
    }
    return index;
}