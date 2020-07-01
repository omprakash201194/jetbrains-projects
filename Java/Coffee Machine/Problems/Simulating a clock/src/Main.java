class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        // implement me
        if (minutes == 59) {
            if (hours == 12) {
                hours = 1;
            } else {
                hours++;
            }
            minutes = 0;
        } else {
            minutes++;
        }
    }
}