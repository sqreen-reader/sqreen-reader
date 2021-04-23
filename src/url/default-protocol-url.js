class DefaultProtocolURL extends URL {
    constructor(url) {
        try {
            super(url);
        } catch (_) {
            super(`https://${url}`);
        }
    }
}

module.exports = DefaultProtocolURL;