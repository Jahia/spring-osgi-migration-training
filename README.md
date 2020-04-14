# Development guidelines

This project answers two questions:
- how to declare an OSGI component instead of using a Spring bean
- how to share services between modules:
    - `training-services`: shareable services
    - `training-components`: components, actions, filters, initializers, interceptors, rules, validators with a dependency with `training-services`
