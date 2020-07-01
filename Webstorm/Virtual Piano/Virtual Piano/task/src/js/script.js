


document.addEventListener("keydown", function (event) {
    switch (event.code) {
        case "KeyA":
            play("A");
            break;
        case "KeyS":
            play("S");
            break;
        case "KeyD":
            play("D");
            break;
        case "KeyF":
            play("F");
            break;
        case "KeyG":
            play("G");
            break;
        case "KeyH":
            play("H");
            break;
        case "KeyJ":
            play("J");
            break;
        case "KeyW":
            play("W");
            break;
        case "KeyE":
            play("E");
            break;
        case "KeyT":
            play("T");
            break;
        case "KeyY":
            play("Y");
            break;
        case "KeyU":
            play("U");
            break;
        default:
            console.warn("An unbound key " + event.key +" is pressed.");
    }
})

function play(key) {
    let audio = document.createElement("AUDIO");
    audio.src = "whiteKeys/"+key+".mp3";
    audio.load();
    audio.play();
}