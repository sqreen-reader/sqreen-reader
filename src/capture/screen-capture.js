
const { desktopCapturer } = require('electron');

class ScreenCapture {
    constructor(options) {
        this.options = options;
    }

    capture() {
        return desktopCapturer.getSources(this.options);
    }
}

module.exports = ScreenCapture;