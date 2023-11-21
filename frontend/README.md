# mar1hotel-website

## Prerequisites
- [Node.js](https://nodejs.org/en/download) 
- [npm](https://www.npmjs.com/) 

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Type Support for `.vue` Imports in TS

TypeScript cannot handle type information for `.vue` imports by default, so we replace the `tsc` CLI with `vue-tsc` for type checking. In editors, we need [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin) to make the TypeScript language service aware of `.vue` types.

If the standalone TypeScript plugin doesn't feel fast enough to you, Volar has also implemented a [Take Over Mode](https://github.com/johnsoncodehk/volar/discussions/471#discussioncomment-1361669) that is more performant. You can enable it by the following steps:

1. Disable the built-in TypeScript Extension
    1) Run `Extensions: Show Built-in Extensions` from VSCode's command palette
    2) Find `TypeScript and JavaScript Language Features`, right click and select `Disable (Workspace)`
2. Reload the VSCode window by running `Developer: Reload Window` from the command palette.

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup
- Change directory to `frontend`
```sh
cd frontend
```
- Install dependencies
```sh
npm install
```
- Compile and Hot-Reload for Development
```sh
npm run dev
```
> Note that the development server is reactive to changes. In another word, simply saving will trigger a refresh on the browser.

- Lint with [ESLint](https://eslint.org/)
```sh
npm run lint
```

- Build for production: Type-Check, Compile and Minify for Production (this is automatically done by the CI/CD pipeline on our GitHub repository)
```sh
npm run build
```

## Pre-setup of the project
- In case you're interested in how the project was initialized:
```sh
cd frontend
npm create vue@latest 
```
> Since we're already in the frontend folder, we can just use put the `Project name` field below to be `.`
```zsh
Vue.js - The Progressive JavaScript Framework

✔ Project name: … .
✔ Package name: … mar1hotel-website
✔ Add TypeScript? … No / [Yes]
✔ Add JSX Support? … [No] / Yes
✔ Add Vue Router for Single Page Application development? … No / [Yes]
✔ Add Pinia for state management? … No / [Yes]
✔ Add Vitest for Unit Testing? … [No] / Yes
✔ Add an End-to-End Testing Solution? › [No]
✔ Add ESLint for code quality? … No / [Yes]
✔ Add Prettier for code formatting? … [No] / Yes

Scaffolding project in [...]/project-group-05/frontend...

Done. Now run:

  npm install
  npm run dev
```
> If the **Project name** was different, `npm` would create a folder with the package name for you instead. **Project name** is different from **Package name**.

- After that, we can add the following dependencies:
```sh
npm install --save axios # for http requests
npm install flowbite flowbite-vue # CSS Utility Library
npm install -D tailwindcss postcss autoprefixer # CSS plugin and preprocessor
```

- For TailwindCSS setup, follow the guide on [setting up tailwindcss with Vue](https://tailwindcss.com/docs/guides/vue-3-vite) to configure `tailwind.config.js` (and more!)


