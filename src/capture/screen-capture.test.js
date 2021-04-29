const ScreenCapturer = require('./screen-capture');
const {expect} = require('chai');

describe('ScreenCapturer', ()=> {
  describe('capture', ()=> {
    it('captures screenshots of the desktop', ()=> {
      const images = [new MockNativeImage()];
      // mock electron desktopCapturer
      const desktopCapturer = {
        getSources() {
          return images;
        },
      };
      const screenCapturer = new ScreenCapturer(desktopCapturer, {});
      const capture = screenCapturer.capture();
      expect(capture).to.equal(images);
    });
  });
});

/**
 * Mock Electron NativeImage class
 */
class MockNativeImage {
  constructor() {
  }
}
