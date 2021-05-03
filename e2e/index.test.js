const path = require('path');
const electronPath = path.join(__dirname, '..',
    'node_modules', '.bin', 'electron');
const Application = require('spectron').Application;
const {expect} = require('chai');

const app = new Application({
  path: electronPath,
  args: [path.join(__dirname, '..')],
});

describe('sQReen Reader', function() {
  this.timeout(30000);

  before(()=>{
    return app.start();
  });

  after(()=>{
    return app.stop();
  });

  it('should have a visible window', ()=>{
    return app.client.getWindowCount().then((count)=>{
      expect(count).to.equal(1);
    });
  });
});
