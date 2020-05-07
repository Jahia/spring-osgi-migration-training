const path = require('path');
const webpack = require('webpack');

module.exports = (env, argv) => {
    return {
        entry: {
            app: path.resolve(__dirname, 'src/jahia/app.js')
        },
        output: {
            path: path.resolve(__dirname, '../main/resources/javascript/apps/'),
            filename: '[name].bundle.js'
        },
        resolve: {
            mainFields: ['module', 'main'],
            extensions: ['.mjs', '.js', '.jsx', 'json']
        },
        module: {
            rules: [
                {
                    test: /\.jsx?$/,
                    include: [path.join(__dirname, 'src/jahia'), path.join(__dirname, 'src/components')],
                    loader: 'babel-loader',
                    query: {
                        presets: [
                            [
                                '@babel/preset-env',
                                {modules: false, targets: {safari: '7', ie: '10'}}
                            ],
                            '@babel/preset-react'
                        ],
                        plugins: ['@babel/plugin-syntax-dynamic-import']
                    },
                },
                {
                    test: /\.s[ac]ss$/i,
                    use: ['style-loader', {
                        loader: 'css-loader',
                        options: {modules: true},
                    }, 'sass-loader']
                }
            ],
        },
        mode: argv.mode,
    };
};
