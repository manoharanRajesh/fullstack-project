export class ProjectEvent {
  message: string;
  mode: string;

  constructor(message: string, mode: string) {
    this.message = message;
    this.mode = mode;
  }
}
