import { Card } from "./card.model";

export interface Game {
  game: [
    {
      gamerId: string;
      mazo: Card[]
    }
  ]
}
