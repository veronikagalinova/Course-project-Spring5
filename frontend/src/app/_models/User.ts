import { Role } from './Role';

export class User {
    id: string;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    role: Role;
    token?: string;
}