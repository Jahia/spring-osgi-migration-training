module.exports = {
    sourceMaps: 'inline',
    presets: ['@babel/preset-react'],
    plugins: [
        '@babel/plugin-transform-classes',
        '@babel/plugin-proposal-class-properties',
        ['@babel/plugin-transform-runtime', {regenerator: true}]
    ]
};
