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
- If you're curious on how I (@notkaramel) started this project, here's what I did:
```sh
cd frontend
npm create vue@latest .
```
> Note that the `.` at the end of the command is because we already have a `frontend` folder. You can see [Vue guide](https://vuejs.org/guide/quick-start.html#creating-a-vue-application) for a more general guide, where the `.` is not needed.
> 
> Another note is that the `Project name` below value is `.` so that npm doesn't create a new folder but instead overwrite the current folder.
```zsh
Vue.js - The Progressive JavaScript Framework

✔ Project name: … .
✔ Add TypeScript? … No / [Yes]
✔ Add JSX Support? … [No] / Yes
✔ Add Vue Router for Single Page Application development? … No / [Yes]
✔ Add Pinia for state management? … No / [Yes]
✔ Add Vitest for Unit Testing? … [No] / Yes
✔ Add an End-to-End Testing Solution? › [No]
✔ Add ESLint for code quality? … No / [Yes]
✔ Add Prettier for code formatting? … [No] / Yes

Scaffolding project in [...]/project-group-05/frontend/frontend...

Done. Now run:

  cd frontend
  npm install
  npm run dev
```
> NOTE: `cd frontend` step is unnecessary if you're already in the frontend folder