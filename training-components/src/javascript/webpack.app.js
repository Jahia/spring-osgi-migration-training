const path = require('path');
const webpack = require('webpack');

module.exports = (env, argv) => {
    return {
        entry: {
            "myapp": path.resolve(__dirname, 'src/jahia/app.js'),
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
        plugins: [
            new webpack.HashedModuleIdsPlugin({
                hashFunction: 'sha256',
                hashDigest: 'hex',
                hashDigestLength: 20
            }),
        ],
        mode: argv.mode,
    };
};
