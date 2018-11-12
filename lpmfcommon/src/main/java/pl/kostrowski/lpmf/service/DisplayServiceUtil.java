package pl.kostrowski.lpmf.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DisplayServiceUtil {

    @SuppressWarnings("RedundantIfStatement")
    public void preparePaging(Model model, Integer pageNo, Long size, Integer PAGE_SIZE) {
        long totalPages = 0;
        if (size % PAGE_SIZE == 0) {
            totalPages = size / PAGE_SIZE;
        } else {
            totalPages = size / PAGE_SIZE + 1;
        }


        long[] fromTo = new long[2];
        boolean[] displayPreviousNext = new boolean[2];

        if (pageNo > 5 && totalPages - pageNo > 5) {
            fromTo[0] = pageNo - 5;
            fromTo[1] = pageNo + 5;
        } else if (pageNo <= 5) {
            fromTo[0] = 1;
            fromTo[1] = 10;
        } else if (totalPages - pageNo <= 5) {
            fromTo[0] = totalPages - 10;
            fromTo[1] = totalPages;
        }

        if (pageNo != 1) {
            displayPreviousNext[0] = true;
        } else {
            displayPreviousNext[0] = false;
        }
        if (pageNo != totalPages) {
            displayPreviousNext[1] = true;
        } else {
            displayPreviousNext[1] = false;
        }

        if (totalPages < 10) {
            fromTo[0] = 1;
            fromTo[1] = totalPages;
        }

        model.addAttribute("pageNo", pageNo);
        model.addAttribute("fromTo", fromTo);
        model.addAttribute("size", size);
        model.addAttribute("displayPreviousNext", displayPreviousNext);
    }
}
