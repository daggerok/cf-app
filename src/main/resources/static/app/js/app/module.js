/**
 * app module config
 */

(function app() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {

    const app = angular.module('app', ['ui.router']);
    app.config([
        '$urlRouterProvider', '$locationProvider', '$stateProvider',
        function config($urlRouterProvider, $locationProvider, $stateProvider) {

          $urlRouterProvider.otherwise('/');
          $locationProvider.html5Mode(true);

          [
            {
              // url: '',
              name: 'app',
              template: `<ui-view/>`,
              abstract: true,
            },
            {
              url: '/',
              name: 'app.me',
              template: `<me></me>`,
            },
            {
              url: '404',
              name: 'app.me.404',
              template: `<me></me>`,
            },
          ].forEach($stateProvider.state);
      }]);
  }
})();
