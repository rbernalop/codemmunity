import { faker } from '@faker-js/faker';

export const randomUser = () => {
    return {
        firstName: faker.name.firstName(),
        lastName: faker.name.lastName(),
        userName: faker.internet.userName(),
        email: faker.internet.email(),
        password: faker.internet.password(),
        birthDate: '1990-01-01'
    }
}

export const registeredUser = () => {
    return {
        firstName: 'John',
        lastName: 'Doe',
        userName: 'john.doe',
        email: 'john.doe@mail.com',
        password: 'password',
        birthDate: '1990-01-01'
    }
}