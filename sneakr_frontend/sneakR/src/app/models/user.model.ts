// user.model.ts
export interface User {
  id: number;
  name: string;   // Mapped from 'nev'
  email: string;
  avatar?: string;
  admin: boolean;
}