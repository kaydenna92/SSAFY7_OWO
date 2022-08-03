module.exports = {
  root: true,
  env: {
    browser: true,
    node: true,
  },
  extends: [
    // vue
    // 'plugin:vue/vue3-essential', // Lv1
    // "plugin:vue/recommended", // Lv2
    "plugin:vue/vue3-recommended",
    "standard",
    // 'plugin:vue/vue3-recommended', // Lv3
    // js
    // "eslint:recommended",
  ],
  plugins: ["html", "standard", "vue"],
  parserOptions: {
    parser: "babel-eslint",
    sourceType: "module",
  },
  rules: {
    "generator-start-spacing": "off",
    "no-debugger": process.env.NODE_ENV == "production" ? "error" : "off",
    "vue/html-closing-bracket-newline": [
      "error",
      {
        singleline: "never",
        multiline: "never", // default값은 "always"
      },
    ],
  },
};
