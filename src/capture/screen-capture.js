class ScreenCapturer {
    constructor(desktopCapturer, options) {
        this.desktopCapturer = desktopCapturer;
        this.options = options;
    }

    capture() {
        return this.desktopCapturer.getSources(this.options);
    }
}

module.exports = ScreenCapturer;