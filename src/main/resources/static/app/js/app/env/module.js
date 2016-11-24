/**
 * env module
 */

(function env() {
  'use strict';

  // document ready
  document.addEventListener('DOMContentLoaded', main);

  function main() {

    const app = angular.module('app');

    /**
     * service
     */

    const EnvModel = function EnvModel($http, $log) {
      return {
        getEnv: getEnv
      };

      function getEnv() {
        return $http.get('/env').then(function env(payload, status, headers, config) {
          $log.info(payload.headers('X-Application-Context'));
          return payload.data || {};
        }, $log.error);
      }
    };

    EnvModel.$inject = ['$http', '$log'];

    app.service('EnvModel', EnvModel);

    /**
     * filter
     */

    const Stringify = function Stringify() {
      return function StringifyFilter(input) {
        return JSON.stringify(input, null, 1);
      }
    };

    app.filter('stringify', Stringify);

    /**
     * controller
     */

    const EnvController = function EnvController(EnvModel) {
      this.env = {};
      setEnv(this);

      function setEnv($ctrl) {
        return EnvModel.getEnv().then(function getEnv(env) {
          return $ctrl.env = env;
        })
      }
    };

    EnvController.$inject = ['EnvModel'];

    /**
     * component
     */

    const envComponent = {
      controller: EnvController,
      template: `<pre>{{ $ctrl.env | stringify }}</pre>`,
    };

    app.component('env', envComponent);
  }
})();
