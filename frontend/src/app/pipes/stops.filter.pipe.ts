import { Pipe, PipeTransform } from '@angular/core';
import { Stop } from '../_models/Stop';

@Pipe({
  name: 'stopsFilter'
})
export class StopsFilterPipe implements PipeTransform {

  transform(items: Stop[], selectedStopLocation: string): Stop[] {
    if (!items || !selectedStopLocation) {
      return items;
    }

    return items.filter(item => item.location !== selectedStopLocation);
  }

}
