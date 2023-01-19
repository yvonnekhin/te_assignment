import { Component } from '@angular/core';
import { FileUploadService } from '../services/file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {


  fileName = '';
  progress = 0;
  message = '';

  text = '';

  constructor(private fileUploadService: FileUploadService) {}

  onFileSelected(event: Event) {
      const target = event.target as HTMLInputElement;
      const files = target.files;
    if (files && files.length > 0) {
      const file:File = files[0];
      if (file) {
        this.fileName = file.name;
        this.fileUploadService.upload(file).subscribe();
      }
    }
  }

}

    
