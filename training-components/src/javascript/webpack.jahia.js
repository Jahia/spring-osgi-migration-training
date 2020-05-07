const path = require('path');
const fs = require('fs');
const webpack = require('webpack');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = (env, argv) => {
    const plugins = [
        new CleanWebpackPlugin({verbose: false}),
        new webpack.HashedModuleIdsPlugin({
            hashFunction: 'sha256',
            hashDigest: 'hex',
            hashDigestLength: 20
        }),
        new CopyWebpackPlugin([{from: 'src/jahia/jahia.json', to: ''}])
    ];

    // Get manifest
    const manifestPath = path.join(__dirname, '../../target/dependency');
    if (fs.existsSync(manifestPath)) {
        const files = fs.readdirSync(manifestPath);
        if (files.length > 0) {
            plugins.push(new webpack.DllReferencePlugin({manifest: require(`../../target/dependency/${files[0]}`)}));
        }
    }

    return {
        entry: {
            "training-components": [
                path.resolve(__dirname, 'src/jahia/publicPath'),
                path.resolve(__dirname, 'src/jahia/index.js')
            ]
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
        plugins: plugins,
        mode: argv.mode,
    };
};
