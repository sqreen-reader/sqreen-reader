const nativeImage = require('electron').nativeImage;

class ScreenCapturer {
  constructor(desktopCapturer, options) {
    this.desktopCapturer = desktopCapturer;
    this.options = options;
  }

  capture() {
    return this.desktopCapturer.getSources(this.options);
  }
}

class SpectronScreenCapturer extends ScreenCapturer {
  capture() {
    const sources = [new SpectronDesktopCapturerSource()];
    return new Promise(((resolve) => {
      resolve(sources);
    }));
  }
}

class SpectronDesktopCapturerSource {
  constructor() {
    this.thumbnail = nativeImage.createFromPath(`${__dirname}/desktop.png`);
  }
}

module.exports = {ScreenCapturer, SpectronScreenCapturer};
