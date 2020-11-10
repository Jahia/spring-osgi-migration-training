const path = require('path');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');

module.exports = (env, argv) => {
    return {
        entry: {
            picker: [path.resolve(__dirname, 'src/components/jahia.js')]
        },
        output: {
            path: path.resolve(__dirname, '../main/resources/javascript/picker/'),
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
                    include: [path.join(__dirname, 'src/components')],
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
        plugins: [new CleanWebpackPlugin({verbose: false})],
        mode: argv.mode,
    };
};
