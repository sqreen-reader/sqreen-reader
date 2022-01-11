process.env['SPECTRON'] = true;
exports.config = {

  specs: [
    './test/specs/**/*.js',
  ],
  exclude: [
  ],
  maxInstances: 10,

  capabilities: [{
    'browserName': 'chrome',
    'goog:chromeOptions': {
      binary: 'node_modules/.bin/electron', // Path to your Electron binary.
      // eslint-disable-next-line max-len
      args: [`app=${__dirname}`, 'headless', 'disable-gpu'], // Optional, perhaps 'app=' + /path/to/your/app/
    },
  }],

  logLevel: 'info',
  bail: 0,

  baseUrl: 'http://localhost',
  waitforTimeout: 10000,
  connectionRetryTimeout: 120000,
  connectionRetryCount: 3,
  services: ['chromedriver'],
  framework: 'mocha',
  reporters: ['spec'],
  mochaOpts: {
    ui: 'bdd',
    timeout: 60000,
  },
};
